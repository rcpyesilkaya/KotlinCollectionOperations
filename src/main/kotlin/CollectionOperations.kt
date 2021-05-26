fun main(args: Array<String>) {

    // ------------------------------------------- Transformation ------------------------------------------------------
     collectionsMap()
    // collectionsZip()
    // collectionsAssociate()
    // collectionsFlatten()
    // collectionsJoin()

    // ------------------------------------------- Filtering -----------------------------------------------------------
    // collectionsFilter()
    // collectionsPartition()
    // collectionsTestPredicates()

    //------------------------------------------- Plus and minus operators ---------------------------------------------
    // collectionsPlusMinus()

    // ------------------------------------------- Grouping ------------------------------------------------------------
    // collectionsGrouping()

    // ------------------------------------------- Retrieve collection parts -------------------------------------------
    // collectionsSlice()
    // collectionsTakeAndDrop()
    // collectionsChunked()
    // collectionsWindowed()

    // ------------------------------------------- Retrieving Single Elements ------------------------------------------
    // collectionsSingleElements()

    // ------------------------------------------- Aggregate operations-------------------------------------------------
    // collectionsAggregate()
    // collectionsReduceAndFold()

    // ------------------------------------------- Ordering ------------------------------------------------------------
    // collectionsOrdering()
}


// ------------------------------------------- Transformation ----------------------------------------------------------
fun collectionsMap() {
    val county = listOf("Turkey", "Italy", "Germany", "Poland")
    println(county.map { it.reversed() }) // [yekruT, ylatI, ynamreG, dnaloP]
    println(county) // [Turkey, Italy, Germany, Poland]

    val mixed = listOf(3, "green", true, "orange", 42)
    println(mixed.mapNotNull { if (it is String) it else null }) // [green, orange]

    val animal = listOf("Dog", "Cat", "Rabbit")
    println(animal.mapIndexed { index, s -> "I have $index $s" }) // [I have 0 Dog, I have 1 Cat, I have 2 Rabbit]
}

fun collectionsZip() {
    val items = listOf("Chair", "Computer", "Glasses")
    val pieces = listOf(100, 60, 35)
    val result: List<Pair<String, Int>> = items.zip(pieces)
    println(result) // [(Chair, 100), (Computer, 60), (Glasses, 35)]

    val job = listOf("Doctor", "Engineer", "Teacher", "Gardener")
    val person = listOf("Ali", "Fatma")
    println(job zip person) // [(Doctor, Ali), (Engineer, Fatma)]

    val items2 = listOf("Bed", "Book")
    val prices = listOf(2000, 70)
    println(items2.zip(prices) { item, price ->
        "20% discount price of the $item = ${price * 0.80} TL"
    })
    //[20% discount price of the Bed = 1600.0TL, 20% discount price of the Book = 56.0TL]

    val animalsPair = listOf("Eagle" to "Black", "Dog" to "Brown", "Cat" to "Yellow")
    val (animals, colors) = animalsPair.unzip()
    println(animals)
    println(colors)
}

fun collectionsAssociate() {
    val fruits = listOf("Apple", "Pear", "Banana")
    println(fruits.associateWith { it.length * 2 }) // {Apple=10, Pear=8, Banana=12}

    val cars = listOf("BMW", "Audi", "Volkswagen")
    println(cars.associateBy { it.toLowerCase() }) // {bmw=BMW, audi=Audi, volkswagen=Volkswagen}
    println(cars.associateBy(
        keySelector = { it.first().toUpperCase() },
        valueTransform = { it.length }
    )) // {B=3, A=4, V=10}
}

fun collectionsFlatten() {
    val temps = listOf(
        listOf(90, 80),
        listOf(true, false, false),
        listOf("Ali", "Ayşe")
    )
    println(temps.flatten())
    // [90, 80, true, false, false, Ali, Ayşe]

    val market = listOf(
        Item(listOf("Watermelon", "Strawberry", "Cherry")),
        Item(listOf("TV", "PC")),
        Item(listOf("T-shirt", "Skirt", "Shoe"))
    )
    println(market.flatMap { it.name })
    // [Watermelon, Strawberry, Cherry, TV, PC, T-shirt, Skirt, Shoe]
}

data class Item(
    val name: List<String>
)

fun collectionsJoin() {
    val animals = listOf("Dog", "Cat", "Snake", "Bear")
    println(animals) // [Dog, Cat, Snake, Bear]
    println(animals.joinToString()) // Dog, Cat, Snake, Bear

    val listString = StringBuffer("Gloory Studio animals: ")
    println(animals.joinTo(listString)) // Gloory Studio animals: Dog, Cat, Snake, Bear

    val employees = listOf("Fatma", "Ayşe", "Ali")
    println(
        employees.joinToString(
            separator = " * ",
            prefix = "Hi Gloory , ",
            postfix = " ,Bye Studio."
        )
    )// Hi Gloory , Fatma * Ayşe * Ali ,Bye Studio.

    val alphabet = 'A'..'Z'
    println(alphabet.joinToString(
        limit = 5,
        truncated = "...",
        transform = {
            "${it.toLowerCase()}"
        }
    )) // a, b, c, d, e, ...
}


// ------------------------------------------- Filtering ---------------------------------------------------------------
fun collectionsFilter() {
    val years = listOf<Int>(1973, 1980, 1986, 1987)
    println(years.filter { it % 3 == 0 }) // [1980, 1986]

    val translations = mapOf("Bag" to "Çanta", "Towel" to "Havlu", "Experience" to "Deneyim")
    println(translations.filter { (key, value) ->
        key.length <= 3 || value == "Deneyim"
    }) // {Bag=Çanta, Experience=Deneyim}

    val vegetables = listOf("cucumber", "tomato", "pepper", "broccoli")
    println(vegetables.filterIndexed { index, s ->
        index > 2 && s == "broccoli"
    }) // [broccoli]

    val jobs = listOf("Detective", "Engineer", "cleaner")
    println(jobs.filterNot { it.length >= 9 }) // [Engineer, cleaner]

    val mix = listOf(true, "Recep", null, 32, 'K', 19L, "Yesilkaya")
    println(mix.filterIsInstance<String>()) // [Recep, Yesilkaya]

    val numbers = listOf(32, null, 72, 19, null)
    println(numbers.filterNotNull()) // [32, 72, 19]
}

fun collectionsPartition() {
    val numbers = listOf(1990, 10, 20, 2021, 30)
    val (big, small) = numbers.partition { it > 1000 }
    println(big) // [1990, 2021]
    println(small) // [10, 20, 30]
}

fun collectionsTestPredicates() {
    val names = listOf("ali", "kamil", "arzu", "zafer")
    println(names.any { it.startsWith("a") }) // true
    println(names.none { it.startsWith("a") }) // false
    println(names.all { it.contains("a") }) // true

    println(emptyList<String>().all { it.length % 3 == 0 }) // true
    println(emptyList<Boolean>().all { it }) // true
    println(emptyList<Int>().all { it > 32 }) // true
}


//------------------------------------------- Plus and minus operators -------------------------------------------------
fun collectionsPlusMinus() {
    val names = listOf("recep", "ayşe", "fatma")
    val plusList = names + listOf("ziya", "sinem")
    val minusList = names - "recep"
    println(names) // [recep, ayşe, fatma]
    println(plusList) // [recep, ayşe, fatma, ziya, sinem]
    println(minusList) // [ayşe, fatma]
}


// ------------------------------------------- Grouping ----------------------------------------------------------------
fun collectionsGrouping() {
    val names = listOf("PERIHAN", "YUSUF", "YAVUZ", "PINAR", "FURKAN")
    println(names.groupBy { it.first().toLowerCase() }) // {p=[PERIHAN, PINAR], y=[YUSUF, YAVUZ], f=[FURKAN]}
    println(names.groupBy(
        keySelector = { it.first() },
        valueTransform = { it.toLowerCase() }
    )) // {P=[perıhan, pınar], Y=[yusuf, yavuz], F=[furkan]}

    println(names.groupingBy { it.first() }.eachCount()) // {P=2, Y=2, F=1}
}


// ------------------------------------------- Retrieve collection parts -----------------------------------------------
fun collectionsSlice() {
    val names = listOf("furkan", "peri", "yusuf", "onur", "cem", "gizem", "mehmet")
    println(names.slice(1..3)) // [peri, yusuf, onur]
    println(names.slice(0 until 6 step 2)) // [furkan, yusuf, cem]
    println(names.slice(listOf(1, 3, 6))) // [peri, onur, mehmet]
}

fun collectionsTakeAndDrop() {
    val numbers = listOf("four", "five", "six", "seven", "eight")
    println(numbers.take(3)) // [four, five, six]
    println(numbers.takeLast(2)) // [seven, eight]
    println(numbers.takeWhile { it.first() == 'f' }) // [four, five]
    println(numbers.takeLastWhile { it.first() == 'z' }) // []

    println(numbers.drop(3)) // [seven, eight]
    println(numbers.dropLast(2)) // [four, five, six]
    println(numbers.dropWhile { it.first() == 'f' }) // [six, seven, eight]
    println(numbers.dropLastWhile { it.first() == 'z' }) // [four, five, six, seven, eight]
}

fun collectionsChunked() {
    val years = (1990..1999).toList()
    println(
        years.chunked(4)
    )//[[1990, 1991, 1992, 1993], [1994, 1995, 1996, 1997], [1998, 1999]]

    println(
        years.chunked(4) { it.average() }
    ) // [1991.5, 1995.5, 1998.5]
}

fun collectionsWindowed() {
    val numbers = (20..25)
    println(
        numbers.windowed(size = 4)
    )// [[20, 21, 22, 23], [21, 22, 23, 24], [22, 23, 24, 25]]

    println(
        numbers.windowed(size = 4, partialWindows = true)
    )// [[20, 21, 22, 23], [21, 22, 23, 24], [22, 23, 24, 25], [23, 24, 25], [24, 25], [25]]

    println(
        numbers.windowed(size = 2, step = 3)
    )// [[20, 21], [23, 24]]

    println(numbers.zipWithNext())// [(20, 21), (21, 22), (22, 23), (23, 24), (24, 25)]
    println(numbers.zipWithNext() { n1, n2 ->
        (n1 + n2) % 3 == 0
    })// [false, false, true, false, false]
}


// ------------------------------------------- Retrieving Single Elements ----------------------------------------------

fun collectionsSingleElements() {
    val numbers = (0..10).toMutableList()

    println(numbers.elementAt(9)) // 9
    println(numbers.first()) // 0
    println(numbers.last()) // 10
    println(numbers.elementAtOrNull(11)) // null

    numbers.elementAtOrElse(11) { index -> numbers += index }
    println(numbers.elementAt(11)) // 11

    println(numbers.firstOrNull { it % 13 == 0 && it != 0 })// null

    println(numbers.random())

    println(numbers.contains(7)) // true
    println(7 in numbers) // true
    println(numbers.containsAll(listOf(1, 3, 13))) // false

    println(numbers.isNullOrEmpty()) // false
    val empty = emptyList<Any>()
    println(empty.isNullOrEmpty()) // true
    println(empty.isNotEmpty()) // false
}


// ------------------------------------------- Aggregate operations-----------------------------------------------------

fun collectionsAggregate() {
    val numbers = listOf(13, 32, 59, 7, 1)
    println(numbers.maxOrNull())// 59
    println(numbers.minOrNull()) // 1
    println(numbers.sum()) // 112
    println(numbers.sumOf { it * 3 }) // 336
    println(numbers.average()) // 22.4
    println(numbers.count()) // 5
    println(numbers.minByOrNull { it % 5 }) // 1

    val numbers2 = listOf("one", "two", "three", "four")
    println(numbers2.maxWithOrNull(compareBy { it.length })) // three
}

fun collectionsReduceAndFold() {
    val numbers = listOf(12, 3, 5, 4, 8)

    println(numbers.reduce { element, sum -> sum + element })// 32
    println(numbers.fold(12) { element, sum -> sum + element })// 44
    println(numbers.foldRight(12) { element, sum -> sum + element })// 44
    println((numbers.reduceIndexed { index, element, sum -> sum + element * index })) // 444
    println(numbers.runningReduce { sum, item -> sum + item }) // [12, 15, 20, 24, 32]
}


// ------------------------------------------- Ordering ----------------------------------------------------------------

class Number(private val major: Int, private val minor: Int) : Comparable<Number> {
    override fun compareTo(other: Number): Int {
        return when {
            this.major != other.major -> {
                this.major - other.major
            }
            this.minor != other.minor -> {
                this.minor - other.minor
            }
            else -> 0
        }
    }
}

fun collectionsOrdering() {
    println(Number(13, 26) <= Number(13, 26)) // true
    println(Number(23, 20) > Number(10, 30)) // true
    println(Number(10, 30) > Number(23, 20)) // false

    val lengthComparator = Comparator { str1: String, str2: String -> str1.length - str2.length }
    println(listOf("x", "yyyy", "cc").sortedWith(lengthComparator)) // [x, cc, yyyy]
    println(listOf("x", "yyyy", "cc").sortedWith(compareBy { it.length })) // [x, cc, yyyy]
    println(listOf(7, 5, 6).sortedWith(compareBy { it % 3 })) // [6, 7, 5]

    val names = listOf("yusuf", "peri", "furkan", "onur", "ahmet")
    println("Sorted: ${names.sorted()}") // [ahmet, furkan, onur, peri, yusuf]
    println("SortedDescending: ${names.sortedDescending()}")// [yusuf, peri, onur, furkan, ahmet]

    println("SortedBy: ${names.sortedBy { it.last() }}") // [yusuf, peri, furkan, onur, ahmet]
    println("SortedByDescending: ${names.sortedByDescending { it.length }}")// [furkan, yusuf, ahmet, peri, onur]

    println("Reversed: ${names.reversed()}")// [ahmet, onur, furkan, peri, yusuf]

    println("Shuffled: ${names.shuffled()}")
}