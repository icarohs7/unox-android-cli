package com.github.icarohs7.domain.commands

import com.github.icarohs7.data.entities.Module
import com.github.icarohs7.domain.commands._base.BaseModuleCreationCommand
import com.github.icarohs7.domain.extensions.createAndroidAppOnDisk
import com.github.icarohs7.domain.extensions.createAndroidLibraryOnDisk
import com.github.icarohs7.domain.extensions.createJvmJavaFxAppOnDisk
import com.github.icarohs7.domain.extensions.createJvmLibraryOnDisk
import com.github.icarohs7.domain.extensions.createJvmMppLibraryOnDisk
import com.github.icarohs7.domain.extensions.createKtorServerAppOnDisk

class AndroidApp : BaseModuleCreationCommand("Generate a new app module") {
    override fun onCreateModule(module: Module) = module.createAndroidAppOnDisk()
}

class AndroidLibrary : BaseModuleCreationCommand("Generate a new library module") {
    override fun onCreateModule(module: Module) = module.createAndroidLibraryOnDisk()
}

class JavafxApp : BaseModuleCreationCommand("Generate a new javafx app module") {
    override fun onCreateModule(module: Module) = module.createJvmJavaFxAppOnDisk()
}

class JvmMppLibrary : BaseModuleCreationCommand("Generate a new multiplatform module with a default jvm source set") {
    override fun onCreateModule(module: Module) = module.createJvmMppLibraryOnDisk()
}

class JvmLibrary : BaseModuleCreationCommand("Generate a jvm library module") {
    override fun onCreateModule(module: Module) = module.createJvmLibraryOnDisk()
}

class KtorServerApp : BaseModuleCreationCommand("Generate a ktor server app module") {
    override fun onCreateModule(module: Module) = module.createKtorServerAppOnDisk()
}