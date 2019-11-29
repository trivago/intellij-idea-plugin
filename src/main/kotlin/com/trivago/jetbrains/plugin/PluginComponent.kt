package com.trivago.jetbrains.plugin

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.FileEditorProvider
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ModuleRootManager
import com.intellij.openapi.roots.OrderEnumerator
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.roots.libraries.Library
import com.intellij.openapi.util.Pair
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiFile
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.testFramework.PsiTestUtil.addLibrary
import com.intellij.util.messages.MessageBus
import java.util.concurrent.atomic.AtomicBoolean

//TODO optimize using https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_services.html ?
class PluginComponent(private val project: Project): ProjectComponent {

    override fun getComponentName(): String {
        return super.getComponentName()
    }

    override fun disposeComponent() {
        super.disposeComponent()
    }

    override fun projectClosed() {
        super.projectClosed()
    }

    override fun initComponent() {
        super.initComponent()
    }

    override fun projectOpened() {
//        val files = FilenameIndex.getAllFilesByExt(project, "kt")
//        files.forEach {
//            PsiFile
//        }
//        registerFileOpenListener()
    }

//    private fun registerFileOpenListener() {
//        val messageBus: MessageBus = project.messageBus
//        val messageBusConnection = messageBus.connect()
//        messageBusConnection.subscribe(
//            FileEditorManagerListener.FILE_EDITOR_MANAGER,
//            object: FileEditorManagerListener {
//                override fun fileOpenedSync(
//                    source: FileEditorManager,
//                    file: VirtualFile,
//                    editors: Pair<Array<FileEditor>, Array<FileEditorProvider>>
//                ) {
////                    if (file.name.endsWith("java")) {
////                        val project = source.project
////                        val module =
////                            ProjectRootManager.getInstance(project).fileIndex.getModuleForFile(file)
////                                ?: return
////                        addLibrary(module)
////                    }
//                }
//            })
//    }

//    private fun addLibrary(module: Module) {
//        val moduleRootManager = ModuleRootManager.getInstance(module)
//        val modifiableRootModel = moduleRootManager.modifiableModel
//        val found = AtomicBoolean(false)
//        OrderEnumerator.orderEntries(module)
//            .forEachLibrary { library1: Library ->
//                if (io.protostuff.jetbrains.plugin.ProtostuffPluginController.LIB_NAME == library1.name) {
//                    found.set(true)
//                    return@forEachLibrary false
//                }
//                true
//            }
//        if (!found.get()) {
//            ApplicationManager.getApplication().runWriteAction {
//                modifiableRootModel.addLibraryEntry(globalLibrary)
//                modifiableRootModel.commit()
//            }
//        }
//    }
}