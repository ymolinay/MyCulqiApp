package com.heyrex.myculqiapp.presentation

import com.heyrex.myculqiapp.core.utils.isValidEmail
import org.junit.Assert
import org.junit.Test

class ExtensionTest {

    @Test
    fun testFormatMoney() {
        Assert.assertTrue("mary.smith@yopmail.com".isValidEmail())
        Assert.assertTrue("mary_smith@yopmail.com".isValidEmail())
        Assert.assertTrue("example@example.com".isValidEmail())
        Assert.assertFalse("mary@smith@yopmail.com".isValidEmail())
        Assert.assertFalse("mary_smith@yopmail".isValidEmail())
        Assert.assertFalse("username@missingdotcom".isValidEmail())
        Assert.assertFalse("username@missingtldcom".isValidEmail())
    }
}