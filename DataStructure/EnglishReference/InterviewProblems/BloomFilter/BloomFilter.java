/*
    A Bloom Filter is a space-efficient probabilistic data structure. It is used to 
    test whether an element is a member of a set. Some applications include spell checking
    , differential file updating, distributed network caches, and textual analysis. It
    is a probabilistic method with a set error rate. Errors can only occur on the side
    of inclusion(??) - A true member will never be reported as not belonging to a set, 
    but some non-members may be reported as members.

    -   Bloom filters use hash transforms to compute a vector (the filter) that is represen-
        tative of the data set. Membership is tested by comparing the results of hashing on
        the potential members of the vector. It is simplest form the vector is composed of
        N elements, each a bit. An element is set if and only if some hasn transform hashes
        to that location for some key. 
*/

import java.util.*;
import java.lang.*;

