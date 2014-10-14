module UsefulFeatures
  def class_name
      self.class.to_s
  end
end

module AnotherModule
  def do_stuff
    puts "This is a test"
  end
end

class Person
  include UsefulFeatures
end

#x = Person.new
#puts x.class_name

include AnotherModule
do_stuff
