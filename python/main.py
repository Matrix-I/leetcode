class Solution(object):
    def mergeAlternately(self, word1, word2) -> str:
        """
        :type word1: str
        :type word2: str
        :rtype: str
        """
        n, m = len(word1), len(word2)
        i = j = 0
        res = []

        while i < n or j < m:
            if i < n:
                res.append(word1[i])
                i += 1
            if j < m:
                res.append(word2[j])
                j += 1
        return "".join(res)

    def reverseWords(self, s):
        """
        :type s: str
        :rtype: str
        """
        return " ".join(s.strip().split()[::-1])

    def increasingTriplet(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        


s = Solution()
# print(s.reverseWords("the sky is blue"))
