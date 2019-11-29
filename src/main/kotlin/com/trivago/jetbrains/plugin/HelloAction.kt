package com.trivago.jetbrains.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class HelloAction: AnAction("Hello") {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        Messages.showMessageDialog(project, "Hellow!", "Greeting", Messages.getInformationIcon())
    }
}