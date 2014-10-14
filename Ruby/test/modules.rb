module NumberStuff
    def NumberStuff.random
        rand(1000000)
    end
end

module LetterStuff
    def LetterStuff.random
        (rand(26) + 65).chr
    end
end

module ToolBox
    class Ruler
        attr_accessor :length
    end
end

module Country
    class Ruler
        attr_accessor :name
    end
end

puts NumberStuff.random
puts LetterStuff.random

a = ToolBox::Ruler.new
a.length = 50

b = Country::Ruler.new
b.name = "Ghengis khan from Moskau"

puts a.length
puts b.name
