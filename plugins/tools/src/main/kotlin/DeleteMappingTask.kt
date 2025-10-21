import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

abstract class DeleteMappingTask : DefaultTask() {
    @get:InputFile
    abstract val inputAarFile: RegularFileProperty

    @get:OutputFile
    abstract val outAarFile: RegularFileProperty

    @TaskAction
    fun doTaskAction() {
        val inputFile = inputAarFile.asFile.get()
        val outputFile = outAarFile.asFile.get()
        val tmpFile = File(inputFile.parentFile, inputFile.name + ".tmp")
        ZipOutputStream(tmpFile.outputStream()).use { outZip ->
            ZipFile(inputFile).use { inZip ->
                for (entry in inZip.entries()) {
                    if (!entry.name.endsWith("proguard.map")) {
                        val newEntry = ZipEntry(entry)
                        outZip.putNextEntry(newEntry)
                        if (!(entry.isDirectory)) {
                            inZip.getInputStream(entry).use { it.copyTo(outZip) }
                        }
                        outZip.closeEntry()
                    }
                }
            }
        }

        if (outputFile.exists()) {
            outputFile.delete()
        }
        tmpFile.renameTo(outputFile)
        logger.log(LogLevel.INFO, "proguard.map file removed")
    }
}