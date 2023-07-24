package earth.health.router

enum class Destination(val link: String) {
    HOME("/"),
    MEALS("/meals"),
    WEIGHT("/weight"),
}
