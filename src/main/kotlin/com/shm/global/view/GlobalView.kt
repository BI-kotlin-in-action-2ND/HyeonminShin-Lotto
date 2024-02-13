package com.shm.global.view

fun welcomePage() {
    val lottoStoreBanner =
        """
         _____                                                                                  _____ 
        ( ___ )                                                                                ( ___ )
         |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   | 
         |   |                                                                                  |   | 
         |   |   __              __    __                 ____    __                            |   | 
         |   |  /\ \            /\ \__/\ \__             /\  _`\ /\ \__                         |   | 
         |   |  \ \ \        ___\ \ ,_\ \ ,_\   ___      \ \,\L\_\ \ ,_\   ___   _ __    __     |   | 
         |   |   \ \ \  __  / __`\ \ \/\ \ \/  / __`\     \/_\__ \\ \ \/  / __`\/\`'__\/'__`\   |   | 
         |   |    \ \ \L\ \/\ \L\ \ \ \_\ \ \_/\ \L\ \      /\ \L\ \ \ \_/\ \L\ \ \ \//\  __/   |   | 
         |   |     \ \____/\ \____/\ \__\\ \__\ \____/      \ `\____\ \__\ \____/\ \_\\ \____\  |   | 
         |   |      \/___/  \/___/  \/__/ \/__/\/___/        \/_____/\/__/\/___/  \/_/ \/____/  |   | 
         |   |                                                                                  |   | 
         |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___| 
        (_____)                                                                                (_____)
        """.trimIndent()
    println(lottoStoreBanner)
}

fun dismissPage() {
    val footer =
        """
          ___   __    __  ____    ____  _  _  ____ 
         / __) /  \  /  \(    \  (  _ \( \/ )(  __)
        ( (_ \(  O )(  O )) D (   ) _ ( )  /  ) _) 
         \___/ \__/  \__/(____/  (____/(__/  (____)            
        """.trimIndent()
    println(footer)
}
