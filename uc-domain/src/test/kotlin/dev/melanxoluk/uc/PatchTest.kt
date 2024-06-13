package dev.melanxoluk.uc

import com.github.difflib.DiffUtils
import org.junit.jupiter.api.Test

class PatchTest {
    
    @Test
    fun f1() {
        val str1 = listOf("str1")
        val str2 = listOf("str2")
        val patch = DiffUtils.diff(str1, str2)
        val patchedTarget = DiffUtils.patch(str1, patch)
        println(patch)
    }
}
