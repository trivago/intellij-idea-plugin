package com.trivago.jetbrains.plugin

import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.psi.impl.source.tree.PsiCommentImpl
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.ProcessingContext
import com.intellij.util.Processor
import com.intellij.util.indexing.FileBasedIndex
import java.util.*

//class PsiProtoClassCommentElement(comment: PsiCommentImpl) : PsiCommentImpl(comment.elementType, comment.text),
//    PsiNamedElement {
//
//    override fun setName(name: String): PsiElement {
//        //noop
//        return this
//    }
//
//    override fun getReference(): PsiReference {
//        return PsiProtoClassCommentReference(this)
//    }
//}

class PsiProtoClassCommentReference(element: PsiCommentImpl) :
    PsiReferenceBase<PsiCommentImpl>(element) {
    override fun resolve(): PsiElement? {
        val path =
            Regex("""^//\ssource:\s(?<path>[\w\/]+.proto)$""").matchEntire(element.text)?.groups?.get("path")?.value

        if (path === null) {
            return null
        }

        val project = element.project
        val pathComponents = path.split("/")

        val fileName = pathComponents.last()

        val filesMatchingJustTheName = FilenameIndex.getFilesByName(project, fileName, GlobalSearchScope.allScope(project))

        val filesMatchingThePath = filesMatchingJustTheName.filter { file ->
            file.virtualFile?.canonicalPath?.contains(path) ?: false
        }

        return filesMatchingThePath.firstOrNull()
    }
}

class PsiProtoClassCommentReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(PsiCommentImpl::class.java), ReferenceProvider()
        )
    }

    internal class ReferenceProvider : PsiReferenceProvider() {
        override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
            if (element is PsiCommentImpl) {
                return arrayOf(PsiProtoClassCommentReference(element))
            }
            return emptyArray()
        }
    }
}