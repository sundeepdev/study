class Square
    def initialize(side_length)
        @side_length = side_length
    end

    def area
        @side_length * @side_length
    end

    #This is a class method
    def self.test_method
        puts "Hello from the Square class!"
    end

    #This is a instance method
    def test_method
        puts "Hello from an instance of class Square!"
    end
end

s1 = Square.new(10)
s1.test_method #call the instance method
Square.test_method #call the class method

