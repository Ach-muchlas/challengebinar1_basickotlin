fun main() {
    /* Declarative list menu */
    val menuItems = listOf(
        "Ayam Bakar" to 50000,
        "Ayam Goreng" to 40000,
        "Ayam Geprek" to 40000,
        "Kulit Ayam Crispy" to 40000,
        "Sate Usus Ayam" to 5000
    )

    val metodePengiriman = listOf(
        "1" to "Take Away",
        "2" to "Delivery"
    )

    /* untuk sistem pilih menu, pembayaran dan pengiriman */
    while (true) {

        /* Displays list menu dari data class */
        for (items in menuItems.indices) {
            val menu = menuItems[items]
            println("${items + 1}. ${menu.first.padEnd(20, ' ')} :  Rp. ${menu.second.toString().padStart(5, ' ')}")
        }

        var totalPayment = 0

        repeat(65) { print("=") }
        println()
        print("Pilih Menu Makanan sesuai dengan angka list (0 untuk selesai) : ")
        val inputMenu = readln().toIntOrNull()
        repeat(65) { print("=") }
        println()

        if (inputMenu == 0) {
            break
        } else if (inputMenu != null && inputMenu in 1..menuItems.size) {
            val selectedMenu = menuItems[inputMenu - 1]
            totalPayment += selectedMenu.second
            println("Kamu memilih menu $inputMenu")
            println("Nama Menu : ${selectedMenu.first}")
            println("harga \t  : ${selectedMenu.second}")
        } else {
            println("Pilihan tidak valid")
        }

        do {
            repeat(65) { print("=") }
            println()
            print("Masukkan Pembayaran : ")
            val inputPayment = readln().toInt()
            val payment = checkPayment(inputPayment, totalPayment)
            println(payment)
        } while (inputPayment != totalPayment)

        repeat(65) { print("=") }
        println()

        for (i in metodePengiriman.indices) {
            val metodePengiriman = metodePengiriman[i]
            println("${metodePengiriman.first}. ${metodePengiriman.second}")
        }

        val inputMetodePengiriman = readln().toIntOrNull()
        if (inputMetodePengiriman == 1) {
            print("Makananmu akan siap (5 detik)")
            for (i in 1..5) {
                Thread.sleep(1000)
                print(".")
            }
            println()
            print("Makananmu Sudah siap Driver segera menuju tempatmu! (5 detik)")
            for (i in 1..5) {
                Thread.sleep(1000)
                print(".")
            }
            println()
            print("Driver Sampai! Pesanan Selesai! (3 detik)")
            for (i in 1..3) {
                Thread.sleep(1000)
                print(".")
            }
            println()
        } else if (inputMetodePengiriman == 2) {
            print("Makananmu akan siap (5 detik)")
            for (i in 1..5) {
                Thread.sleep(1000)
                print(".")
            }
            println()
            print("Makananmu Sudah siap Driver segera menuju tempatmu! (5 detik)")
            for (i in 1..5) {
                Thread.sleep(1000)
                print(".")
            }
            println()
            print("Driver Sampai! Pesanan Selesai! (3 detik)")
            for (i in 1..3) {
                Thread.sleep(1000)
                print(".")
            }
            println()
        } else {
            println("invalid number choice")
        }
    }
}

private fun checkPayment(inputPayment: Int, totalPayment: Int): String =
    if (inputPayment == totalPayment) "Berhasil" else if (inputPayment < totalPayment) "Pembayaran anda kurang ${totalPayment - inputPayment}" else "Pembayaran anda lebih ${totalPayment - inputPayment}"


