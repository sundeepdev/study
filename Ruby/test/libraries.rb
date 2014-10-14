#require 'net/http'
#Net::HTTP.get_print('www.rubyinside.com', '/')

Person = Struct.new(:name, :age)
me = Person.new("Fred Bloggs", 25)
me.age += 1

puts me.age
puts me.name
