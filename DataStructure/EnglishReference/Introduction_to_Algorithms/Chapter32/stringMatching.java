/*
 *  String Matching:
 *  
 *  -   String-matching can greatly aid the responsiveness of the text
 *      editing program. Among their many other applications, sting
 *      matching algorithms search for particular patterns in DNA
 *      sequences. Internet search engines also use them to find Web
 *      pages relevant to queries.
 *
 *      The problem is easy to formalize: We assume that the text is
 *      an array T[1..n] of length n and that the pattern is an array
 *      P[1..m] of length m<=n. We say that pattern P occurs with shift
 *      s in text T (or, equivalently, that pattern P occurs beginning
 *      at position s + 1 in text T) if o<=s<=n-m. and T[s+1..s+m] = P[1..m]
 *      (that is if T[s+j] = P[j], for 1<=j<=m). If P occurs with shift s
 *      in T, we call s is a valid shift; otherwise, we call s an invalid
 *      shift. The string-matching is the problem of finding all valid
 *      shifts with which a given pattern P occurs in a given text T.
 *
 *  -   Algorithms overview
 *
 *      Algorithm           Preprocessing time          Matching time
 *      Naive               0                           O((n-m+1)m)
 *      Rabin-Karp          delta(m)                    O((n-m+1)m)
 *      Finite automation   O(m|E|)                     delta(n)
 *      Knuth-Morris-Pratt  O(m)                     delta(n)
 *
 *      note: We denote by "E*"(read "sigma-star") the set set of all finite-length strings formed using characters
 *      from alphabet E. We also consider the zero-length empty string, denoted "e" also belongs
 *      to E*.
 *      
 */
