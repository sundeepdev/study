# ([a-w] AND ([^c-g] OR z))
# This is equivalent to: /[abh-w]/
puts 'ayz' =~ /[a-w&&[^c-g]z]/

