const Main = require("../../src/Main");

let executor;

beforeEach(()=> {
    executor = new Main();
})

describe('Basic tests', () => {
    it('should check that the number is a 01-palindrome', () => {
        expect(executor.isPalindrome(121)).toBeTruthy();
    });

    it('should check that the negative number is a 01-palindrome', () => {
        expect(executor.isPalindrome(-121)).toBeFalsy();
    });

    it('should check that the 2 size number is a 01-palindrome', () => {
        expect(executor.isPalindrome(10)).toBeFalsy();
    });
});
