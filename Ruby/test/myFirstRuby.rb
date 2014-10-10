lines = File.readlines("text.txt") #IO.readlines method returns an array
line_count = lines.size #get the size of lines in the text file
text = lines.join #the Array.join method transfers an array into a string
character_count = text.length #Of fourse the String.length returns the size 
character_count_nospaces = text.gsub(/\s+/, '').length
#   The regular expression is described as the following:
#   /\.|\?!/
#   1.  The forward slashes at the start and the end are the usual delimiters
#       for a regular expression.
#   2.  The first section is \., and this represents a full stop. Why you need the backslash upfront
#       because . represents "any character" so it needs to be escaped.
#   3.  the question mark ? represents "zero or one instances of the previous character" so it needs to be
#       escaped too
#   4.  The ! is not escaped.
#   5.  
#
#
#
#
#
sentence_count = text.split(/\.|!|\?/).length
paragraph_count = text.split(/\n\n/).length
word_count = text.split.length

puts "#{line_count} lines"
puts "#{character_count} characters"
puts "#{character_count_nospaces} character (excluding spaces)"
puts "#{sentence_count} sentences"
puts "#{paragraph_count} paragraphs"
puts "#{sentence_count / paragraph_count} sentences per paragraph (average)"
puts "#{word_count / sentence_count} words per sentence (average)"

text_2 = %q{Los Angeles has some of the nicest weather in the country.}
stopwords = %w{the a by on for of are with just but and to the my in I has some}

words = text_2.scan(/\w+/)
#select is a method available to all arrays and hashes that returns the elements of that array
#or hash that matches the expression in the code block.
keywords = words.select{|word| !stopwords.include?(word)}
keyword_percent = ((keywords.length.to_f / words.length.to_f) *  100).to_i
puts "#{keyword_percent} % keyword percent"


