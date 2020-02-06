PSI
Very good diagram: https://www.jetbrains.org/intellij/sdk/docs/reference_guide/custom_language_support/implementing_parser_and_psi.html
it's the AST (tree ot tokens (generated by the Lexer)) + semantic + methods
The easiest way to get the expected PSI structure for any file is to use PSI Viewer. Run the project and call Tools → View PSI Structure.
Unlike VirtualFile and Document, which have application scope (even if multiple projects are open, each file is represented by the same VirtualFile instance), PSI has project scope (the same file is represented by multiple PsiFile instances if the file belongs to multiple projects open at the same time).

References and resolve
E.g. to go to the declaration/navigate or to find usages
https://www.jetbrains.org/intellij/sdk/docs/reference_guide/custom_language_support/references_and_resolve.html
https://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support/reference_contributor.html
All PSI elements which work as references (for which the Go to Declaration action applies) need to implement the PsiElement.getReference() method and to return a PsiReference implementation from that method.
An element can also contain multiple references (for example, a string literal can contain multiple substrings which are valid full-qualified class na

Find usages
https://www.jetbrains.org/intellij/sdk/docs/reference_guide/custom_language_support/find_usages.html 

How do I find a file if I know its name but don’t know the path?
FilenameIndex.getFilesByName()

How do I find where a particular PSI element is used?
ReferencesSearch.search()

How do I get a virtual file?
From an action: e.getData(PlatformDataKeys.VIRTUAL_FILE). If you are interested in multiple selection, you can also use e.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY).
From a path in the local file system: LocalFileSystem.getInstance().findFileByIoFile()
From a PSI file: psiFile.getVirtualFile() (may return null if the PSI file exists only in memory)
From a document: FileDocumentManager.getInstance().getFile()

How do I get notified when VFS changes?
The VirtualFileManager.addVirtualFileListener() method allows you to receive notifications about all changes in the VFS.

Are there any utilities for analyzing and manipulating virtual files?
VfsUtil and VfsUtilCore provide utility methods for analyzing files in the Virtual File System.
You can use ProjectLocator to find the projects that contain a given virtual file.

Plugin dependency 
https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_dependencies.html
build.gradle:
intellij {
    plugins 'org.jetbrains.kotlin:1.3.11-release-IJ2018.3-1'
}

plugin.xml
<depends>org.jetbrains.kotlin</depends>