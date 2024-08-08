class Main {

    isPalindrome(number) {
        return String(number) === this.reverseString(number);
    }

    reverseString(number) {
        return String(number).split('').reverse().join('');
    }

}

module.exports = Main;