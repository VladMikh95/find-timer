package ml.vladmikh.projects.find_timer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform