const Main = require("../../src/Main");

let executor;

beforeEach(() => {
    executor = new Main();
})

describe('Basic tests', () => {
    it('should return true', () => {
        expect(executor.isValidParenthesis('()')).toBeTruthy();
    });

    it('should return true', () => {
        expect(executor.isValidParenthesis('()[]{}')).toBeTruthy();
    });

    it('should return false', () => {
        expect(executor.isValidParenthesis('(]')).toBeFalsy();
    });

    it('should return true', () => {
        expect(executor.isValidParenthesis('([])')).toBeTruthy();
    });

    it('should return false', () => {
        expect(executor.isValidParenthesis('([)]')).toBeFalsy();
    });

    it('should return true', () => {
        expect(executor.isValidParenthesis('()[{}]([]){}')).toBeTruthy();
    });
});
