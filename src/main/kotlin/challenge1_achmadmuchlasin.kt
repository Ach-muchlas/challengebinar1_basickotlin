data class MenuItem(val name: String, val price: Int)

class Order(private val menuItems: List<MenuItem>) {
    private val selectedItem = mutableListOf<MenuItem>()

    /*function to display list menu*/
    fun displayMenu() {
        for ((index, item) in menuItems.withIndex()) {
            println("${index + 1}. ${item.name.padEnd(20, ' ')} : ${item.price.toString().padStart(14, ' ')}")
        }
    }

    /*function to delete all previous data*/
    fun clearSelectedItems() {
        selectedItem.clear()
    }

    /*function display user input*/
    fun selectedMenu(itemIndex: Int) {
        if (itemIndex in 1..menuItems.size) {
            selectedItem.add(menuItems[itemIndex - 1])
            println("Kamu memmilih menu $itemIndex")
            println("Nama Menu : ${menuItems[itemIndex - 1].name}")
            println("Harga \t  : ${menuItems[itemIndex - 1].price}")
        } else {
            println("Pilihan Tidak valid")
        }
    }

    /* function to calculate total payment */
    fun calculateTotalPayment(): Int = selectedItem.sumOf { it.price }
}

class PaymentProcess {

    /* function to check user payment */
    fun processPayment(inputPayment: Int, totalPayment: Int): String {
        return if (inputPayment == totalPayment) {
            "Terima kasih, Anda berhasil memsan makanan"
        } else if (inputPayment < totalPayment) {
            """Maaf, pembayaran Anda gagal 
                |karena pembayaran anda kurang ${inputPayment - totalPayment}
            """.trimMargin()
        } else {
            """Terima kasih, Anda berhasil memesan makanan
                |Berikut kembalian kelebihan pembayaran anda ${inputPayment - totalPayment}
            """.trimMargin()
        }
    }
}

interface DeliveryMethod {
    fun initiateDelivery()
    fun initiateNameMethod(): String
}

class TakeAway : DeliveryMethod {
    override fun initiateDelivery() {
        print("Makananmu sudah siap! Silakan ambil di resto ya! (5 detik)")
        for (i in 1..5) {
            Thread.sleep(1000)
            print(".")
        }
        println()
    }

    override fun initiateNameMethod(): String {
        return "Take Away"
    }
}

class Delivery : DeliveryMethod {
    override fun initiateDelivery() {
        print("Driver dalam perjalanan! (5 detik)")
        for (i in 1..5) {
            Thread.sleep(1000)
            print(".")
        }
        println()
        print("Driver sampai! ")
    }

    override fun initiateNameMethod(): String {
        return "Delivery"
    }
}

class DeliveryService {
    fun initiateDelivery(deliveryMethod: DeliveryMethod) {
        print("Makanan akan siap (5 detik)")
        for (i in 1..5) {
            Thread.sleep(1000)
            print(".")
        }
        println()
        deliveryMethod.initiateDelivery()
        print("Pesanan Selesai! (3 detik)")
        for (i in 1..3) {
            Thread.sleep(1000)
            print(".")
        }
        println()
    }
}

fun main() {

    val menuItems = listOf(
        MenuItem("Ayam Bakar", 50000),
        MenuItem("Ayam Goreng", 40000),
        MenuItem("Ayam Geprek", 40000),
        MenuItem("Kulit Ayam Crispy", 40000),
        MenuItem("Sate Usus Ayam", 5000),
    )

//    var inputPayment : Int

    /*initialization class*/
    val order = Order(menuItems)
    val paymentProcess = PaymentProcess()
    val deliveryService = DeliveryService()
    val takeAway = TakeAway()
    val delivery = Delivery()

    while (true) {

        var inputMethod: Int

        /*call function display from class order*/
        println()
        order.displayMenu()
        repeat(40) { print("=") }
        println()

        print("Pilih Menu Makanan sesuai dengan angka list (0 untuk selesai) : ")
        val inputMenu = readln().toIntOrNull() ?: continue
        repeat(40) { print("=") }
        println()

        if (inputMenu == 0) {
            break
        } else {
            order.selectedMenu(inputMenu)
        }

        val totalPayment = order.calculateTotalPayment()

        do {
            repeat(40) { print("=") }
            println()
            print("Masukkan Pembayaran : ")
            val inputPayment = readln().toInt()
            val paymentResult = paymentProcess.processPayment(inputPayment, totalPayment)
            println(paymentResult)
        } while (inputPayment < totalPayment)

        repeat(40) { print("=") }
        println()

        for ((index, method) in listOf(takeAway.initiateNameMethod(), delivery.initiateNameMethod()).withIndex()){
            println("${index + 1}. $method")
        }


        do {
            print("pilih metode pengantaran makanan yang kamu inginm kan, pilih sesuai dengan angka yang ada : ")
            inputMethod = readln().toInt()
            when (inputMethod) {
                1 -> {
                    deliveryService.initiateDelivery(takeAway)
                    break
                }

                2 -> {
                    deliveryService.initiateDelivery(delivery)
                    break
                }

                else -> {
                    println("Try Again, Your choose is wrong")
                }
            }
        } while (true)


        // clear data
        order.clearSelectedItems()
    }
}
