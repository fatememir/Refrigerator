package com.example.refrigerator

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import java.io.UnsupportedEncodingException

class Utils {
    companion object {
        fun hideKeyboard(activity: Activity, context: Context, view: View) {
            activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            view.requestFocus()
            view.clearFocus()
            val focusedView = activity.currentFocus
            focusedView?.let { v ->
                val imm =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }

        fun fixUnicode(string: String): String {
            var name = ""
            try {
                name = String(string.toByteArray(Charsets.ISO_8859_1), Charsets.UTF_8)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
            return name
        }

//        fun pricesFixer(response: Float): String {
//            if (response.toInt().toString().length < 4) {
//                val decimalFormat = DecimalFormat("000")
//                return decimalFormat.format(response)
//            }
//            val decimalFormat = DecimalFormat("0,000")
//            return decimalFormat.format(response)
//        }
//
//        fun pricesFixer(response: Int): String {
//            if (response.toString().length < 4) {
//                val decimalFormat = DecimalFormat("000")
//                return decimalFormat.format(response)
//            }
//            val decimalFormat = DecimalFormat("0,000")
//            return decimalFormat.format(response)
//        }
//
//        fun pricesFixer(response: String): String {
//            if (response.length < 4) {
//                val decimalFormat = DecimalFormat("000")
//                return decimalFormat.format(response)
//            }
//            val decimalFormat = DecimalFormat("0,000")
//            return decimalFormat.format(response)
//        }

        fun pricesFixer(int: Int): String {
            var tempString = ""
            tempString = stringReverser(int.toString())
            tempString = comma(tempString)
            tempString = stringReverser(tempString)
            return tempString
        }

        fun pricesFixer(float: Float): String {
            var tempString = ""
            tempString = stringReverser(float.toInt().toString())
            tempString = comma(tempString)
            tempString = stringReverser(tempString)
            return tempString
        }

        fun pricesFixer(string: String): String {
            var tempString = ""
            tempString = stringReverser(string)
            tempString = comma(tempString)
            tempString = stringReverser(tempString)
            return tempString
        }

        private fun stringReverser(str: String): String {
            var number = 0
            /* The split() method of String class splits
             * a string in several strings based on the
             * delimiter passed as an argument to it
             */
            val words = str.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            var reversedString = ""
            for (i in words.indices) {
                val word = words[i]
                var reverseWord = ""
                for (j in word.length - 1 downTo 0) {
                    /* The charAt() function returns the character
                     * at the given position in a string
                     */
                    reverseWord += word[j]
                    number++
                }
                reversedString = "$reversedString$reverseWord "
            }
            return reversedString
        }

        private fun comma(str: String): String {
            var tempString = ""
            for (i in str.indices) {
                if (i % 3 == 0 && i != 0 && i + 1 != str.length) {
                    tempString += ","
                }

                tempString += str.substring(i, i + 1)
            }
            return tempString

        }
    }
}