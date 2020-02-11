package com.ehi.plugin.task

import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.api.ApplicationVariantImpl
import com.android.build.gradle.internal.api.BaseVariantImpl
import com.ehi.plugin.spi.VariantProcessor
import com.google.auto.service.AutoService

/**
 * Author: Omooo
 * Date: 2020/2/11
 * Version: v0.1.1
 * Desc: 注册 Convert2WebpTask
 * @see Convert2WebpTask
 */
@AutoService(VariantProcessor::class)
class Convert2WebpVariantProcessor : VariantProcessor {

    override fun process(variant: BaseVariant) {
        val variantImpl = variant as BaseVariantImpl
        println("/*******************************/")
        variantImpl.allRawAndroidResources.files.iterator().forEach {parentFile ->
            if (parentFile.isDirectory){
                parentFile.listFiles()?.iterator()?.forEach {
                    println(it.absolutePath)
                }
            }else{
                println(parentFile.absolutePath)
            }
        }
        println("/*******************************/")

        val variantData = (variant as ApplicationVariantImpl).variantData
        val tasks = variantData.scope.globalScope.project.tasks
        val convert2WebpTask = tasks.findByName("convert2Webp") ?: tasks.create(
            "convert2Webp",
            Convert2WebpTask::class.java
        )

    }
}