args.foreach(println)
for(arg <- args)
println(arg)

def functionalPrintArgs(args: Array[String]) = args.mkString("\n")

println(functionalPrintArgs(args))