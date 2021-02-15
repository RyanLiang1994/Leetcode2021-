/*
1108. Defanging an IP Address
Given a valid (IPv4) IP address, return a defanged version of that IP address.

A defanged IP address replaces every period "." with "[.]".



Example 1:

Input: address = "1.1.1.1"
Output: "1[.]1[.]1[.]1"
Example 2:

Input: address = "255.100.50.0"
Output: "255[.]100[.]50[.]0"


Constraints:

The given address is a valid IPv4 address.
 */


// Replace fastest, join-split second fast, brute-fore slowest
class Solution {
    String defanged = "[.]";
    public String defangIPaddr1(String address) {
        String result = String.join(defanged, address.split("\\."));

        return result;
    }
    public String defangIPaddr(String address) {

        String result = address.replace(".", defanged);

        return result;
    }
    public String defangIPaddr2(String address) {

        String result = "" + address.charAt(0);
        for (int i = 1; i < address.length(); i++) {
            char temp = address.charAt(i);
            if ( temp == '.') {
                result += defanged;
            } else {
                result += temp;
            }
        }
        return result;
    }
}