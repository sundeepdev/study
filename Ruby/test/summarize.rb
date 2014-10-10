#Summarizing by Finding "Interesting" Sentences

text = %q{
Ruby is a great programming language. It is object oriented
and has many groovy features. Some people don't like it, but that's
not our problem! It's easy to learn. It's great. To learn more about Ruby,
visit the official Ruby Web site today.
}


#.grub(/\s+/, " ") replaces mutliple whitespace with a single whitespace
#strip removes the leading and trailing whitespace.
#split just splits the string into an array
sentences = text.gsub(/\s+/, " ").strip.split(/\.|\?|!/)
sentences_sorted = sentences.sort_by {|sentence| sentence.length}
#get the number of elements in the first third
puts "sentences_sorted.length: #{sentences_sorted.length}"
one_third = sentences_sorted.length / 3
puts "one third: #{one_third}"
#get the second third elements with String's slice method
#noted that you grab one extra element to compensate for
#rounding caused by integer division(??). Let's if there are 6
#elements in the ideal_sentences and 
ideal_sentences = sentences_sorted.slice(one_third, one_third + 1)

#The operator =~ checks to see if the sentence includes the word "is"
#or "are". If yes, it returns the offset else return nil
ideal_sentences = ideal_sentences.select { |sentence| sentence =~ /is|are/ } 
puts ideal_sentences.join(". ")
