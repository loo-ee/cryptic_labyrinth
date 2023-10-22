package csc200.louie;

public class Questionnaire {
    private final static String[][][] quizQuestionsAndAnswers = {
        // Set 1 (Easy - Yes or No)
        {
            {"Question 1: Is the Caesar cipher a substitution cipher used in cryptography?","Yes"},
            {"Question 2: Does RSA encryption use both a public key and a private key?","Yes"},
            {"Question 3: Is TLS/SSL commonly used for secure web communication (HTTPS)?","Yes"},
            {"Question 4: Does symmetric-key cryptography use the same key for both encryption and decryption?","Yes"},
            {"Question 5: Does the Diffie-Hellman key exchange algorithm involve a public-private key pair?","Yes"},
            {"Question 6: In binary, is the result of XOR (exclusive OR) for 1 and 0 equal to 1?","Yes"},
            {"Question 7: Is bcrypt commonly used to hash passwords for storage?","Yes"},
            {"Question 8: Is the Big O notation used to describe the upper bound of time complexity in algorithm analysis?","Yes"},
            {"Question 9: Is AES (Advanced Encryption Standard) an encryption algorithm?","Yes"},
            {"Question 10: Does QuickSort have an average-case time complexity of O(n^2)?","No"}
        },
        // Set 2 (Medium - Single Word Answers)
        {
            {"Question 1: What is the purpose of a rainbow table in password cracking?","Reversing"},
            {"Question 2: In public-key cryptography, what does 'RSA' stand for?","Rivest"},
            {"Question 3: Which encryption algorithm is commonly used for secure web communication?","TLS"},
            {"Question 4: What is the term for a secret key used in symmetric-key cryptography?","Shared"},
            {"Question 5: What is the fundamental principle behind the Diffie-Hellman key exchange algorithm?","Exchange"},
            {"Question 6: In binary, what is the result of XOR (exclusive OR) for 1 and 0?","1"},
            {"Question 7: Which algorithm is commonly used to hash passwords for storage?","bcrypt"},
            {"Question 8: What is the Big O notation used for in algorithm analysis?","Complexity"},
            {"Question 9: What does 'AES' stand for in cryptography?","Advanced"},
            {"Question 10: Which sorting algorithm has an average-case time complexity of O(n log n)?","QuickSort"}
        },
        // Set 3 (Hard - Single Word Answers)
        {
            {"Question 1: In elliptic curve cryptography, what does the 'elliptic curve' refer to?","Curve"},
            {"Question 2: What is the primary goal of quantum-resistant cryptography?","Security"},
            {"Question 3: What is the significance of the Rijndael algorithm in cryptography?","AES"},
            {"Question 4: In graph theory, what is the chromatic number of a graph?","Colors"},
            {"Question 5: What is the purpose of a B-tree data structure in computer science?","Data"},
            {"Question 6: Which sorting algorithm is known for its average-case time complexity of O(n log n)?","MergeSort"},
            {"Question 7: What is the Traveling Salesman Problem (TSP) in computer science?","Route"},
            {"Question 8: What is the primary use of the Euclidean algorithm in mathematics and computer science?","GCD"},
            {"Question 9: In cryptography, what is a one-time pad (OTP)?","Perfect"},
            {"Question 10: What is the difference between P and NP problems in computational complexity theory?","Polynomial"}
        }
    };

    public static String[][][] getQuizQuestionsAndAnswers() {
        return Questionnaire.quizQuestionsAndAnswers;
    }

}
