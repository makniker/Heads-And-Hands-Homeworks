import java.util.*

class Game {
    private fun chooseClassForHero(): Hero {
        println("Choose your hero class:")
        println("1. Warrior")
        println("2. Mage")
        println("3. Rogue")
        while (true) {
            try {
                val heroChoice = readln().toInt()
                when (heroChoice) {
                    1 -> {
                        return Hero("Warrior", 20, 20, 120, 10, 30)
                    }

                    2 -> {
                        return Hero("Mage", 20, 20, 100, 5, 50)
                    }

                    3 -> {
                        return Hero("Rogue", 25, 15, 100, 10, 14)
                    }

                    else -> println("Invalid choice.")
                }
            } catch (e: InputMismatchException) {
                println("Type a number!")
            }
        }
    }

    fun start() {
        val hero = chooseClassForHero()
        println("You made your choice...\nDark dungeon beckons you with its treasures...")
        val level = Level()
        while (hero.isAlive && !level.isLevelFinished) {
            try {
                hero.printStats()
                println("Choose your action")
                println("1. Bravely step into next room")
                println("2. Ask Gods for help")
                println("3. Leave this place")
                val action = readln().toInt()
                when (action) {
                    1 -> level.stepIntoRandomFight(hero)
                    2 -> {
                        if (hero.heal()) {
                            println("You were healed with ancient magic")
                        } else {
                            println("Nothing happens...")
                        }
                    }

                    3 -> {
                        println("You finished your journey in this Dungeon.")
                        level.finish()
                    }

                    else -> println("Enter right command, please")
                }
            } catch (e: Exception) {
                println("Enter right command, please")
            }
        }
    }
}
