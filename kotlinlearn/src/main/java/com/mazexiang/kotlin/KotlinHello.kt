package com.mazexiang.kotlin

/**
 * @author : mazexiang
 * @date : 19/2/22
 */

class Greeter(val name: String) {
    fun greet() {
        println("Hello, $name")
    }
}

fun method(args: Array<String>) {
    Greeter(args[0]).greet()
}

fun main() { // 只是一个普通函数
    println("Hello World")
}
fun main(args: Array<String>){
    printProduct("1","5")
    printProduct("xx","6")
    println(describe(2))
    println( describe("abc") )
}

fun parseInt(str: String):Int?{
    return str.toIntOrNull()
}

fun printProduct(arg1:String,arg2:String) {
    val a = parseInt(arg1)
    val b = parseInt(arg2);
    if (a!=null && b!=null){
        println(a+b)
    }else{
        println("either $arg1 is not a number or $arg2 is not a number!")
    }
}

fun describe(obj:Any):String = // 类似于java 的switch
    when(obj is String){
        true -> obj.toString().toUpperCase()
        false -> parseInt(obj.toString()).toString()+"test"
    }

fun describe2(obj: Any): String =// 类似于java 的switch
        when (obj) {
            1          -> "One"
            "Hello"    -> "Greeting"
            is Long    -> "Long"
            !is String -> "Not a string"
            else       -> "Unknown"
        }



