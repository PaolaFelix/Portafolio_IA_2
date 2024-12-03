// Databricks notebook source
//comment
val courseName : String = "Introduction"

// COMMAND ----------

var courseName : String = "Introduction"

// COMMAND ----------

courseName = "Tema 2"

// COMMAND ----------

val myName = "Paola"

// COMMAND ----------

val edad: Int = 25

// COMMAND ----------

val edad = 26

// COMMAND ----------

var edad : Int = 25

// COMMAND ----------

edad = 30

// COMMAND ----------

edad = "hola"

// COMMAND ----------

def square(x:Int) : Int = x*x

// COMMAND ----------

square(5)

// COMMAND ----------

def cube(x:Int) : Int = x*x*x 

// COMMAND ----------

cube(10)

// COMMAND ----------

object HelloWorld{
  def main(args:Array[String]): Unit = 
    println("Hello World")
}

// COMMAND ----------

HelloWorld.main(Array.empty)

// COMMAND ----------

5 < 3

// COMMAND ----------

val numero : Int = 5
val decimal : Double = 5.5

// COMMAND ----------

def logicalOr (a:Boolean, b:Boolean) : Boolean =
{
  println("This is the logical of your parameters")
  a || b
}

// COMMAND ----------

def logicalAnd(a: Boolean, b: Boolean): Boolean =
{
  println("This is the logical and of your paramters")
  a && b
}

// COMMAND ----------

logicalOr(true, false)

// COMMAND ----------

logicalAnd(true, false)

// COMMAND ----------

val tt = logicalOr(true,true)
val ft = logicalOr(false,true)
val ff = logicalOr(false,false)
val tf = logicalOr(true,false)

// COMMAND ----------

// MAGIC %md
// MAGIC XOR function

// COMMAND ----------

def xor(a: Boolean, b: Boolean): Boolean =
{
  if (a == b){
    return(false)
  } else{
    return(true)
  }
}

// COMMAND ----------

val tt = xor(true,true)
val ft = xor(false,true)
val ff = xor(false,false)
val tf = xor(true,false)

// COMMAND ----------

def xnor 

// COMMAND ----------

util.Random.nextInt

// COMMAND ----------

// val evaluates when defined, def - when called:
val test1: () => Int = {
 val r = util.Random.nextInt
 () => r
}


lazy val test3: () => Int = {
 val r = util.Random.nextInt
 () => r
}


def test2: () => Int = {
 val r = util.Random.nextInt
 () => r
}


// COMMAND ----------

test1()

// COMMAND ----------

test3()

// COMMAND ----------

test2()

// COMMAND ----------

object GFG
{
    // Function to calculate
    // factorial using Recursive
    // formula (i.e N! = N * N-1 !)
    def factorial(n: Int): Int =
    {
        if (n == 0)
            return 1
        else
            return n * factorial(n-1)
    }
 
    // Driver Code
    def main(args: Array[String])
    {
        println(factorial(5))
    }
}

