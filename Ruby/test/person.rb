class Person
    def initialize(name)
        set_name(name)
    end
        
    def name
        #@first_name + ' ' + @last_name
        print_name
    end

    private 

    def set_name(name)
        first_name, last_name = name.split(/\s+/)
        set_first_name(first_name)
        set_last_name(last_name)
    end

    def set_first_name(name)
        @first_name = name
    end

    def set_last_name(name)
        @last_name = name
    end

    def print_name
        puts @first_name + ' ' + @last_name
    end

    protected :print_name
end

class Student < Person
end


#person_instance = Person.new("Alex Yu")
#person_instance.name
student_instance = Student.new("Alex Wu")
student_instance.name

