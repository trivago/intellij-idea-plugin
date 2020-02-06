package com.trivago.jetbrains.plugin

import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.psi.impl.source.tree.PsiCommentImpl
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.ProcessingContext

class PsiProtoClassCommentReference(element: PsiCommentImpl) :
    PsiPolyVariantReferenceBase<PsiCommentImpl>(element) {

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val path =
            Regex("""^//\ssource:\s(?<path>[\w\/]+.proto)$""").matchEntire(element.text)?.groups?.get("path")?.value

        if (path === null) {
            return emptyArray()
        }

        val project = element.project
        val pathComponents = path.split("/")

        val fileName = pathComponents.last()

        val filesMatchingJustTheName =
            FilenameIndex.getFilesByName(project, fileName, GlobalSearchScope.allScope(project))

        val elements = filesMatchingJustTheName.filter { file ->
            file.virtualFile?.canonicalPath?.contains(path) ?: false
        }

        return PsiElementResolveResult.createResults(elements)
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