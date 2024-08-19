const Main = require("../../src/Main");

let executor;

beforeEach(()=> {
    executor = new Main();
})

describe('Basic tests', () => {
    it('should return the number 5', () => {
        expect(executor.transformRomanToInteger("V")).toEqual(5);
    });

    it('should return the number 2', () => {
        expect(executor.transformRomanToInteger("II")).toEqual(2);
    });

    it('should return the number 7', () => {
        expect(executor.transformRomanToInteger("VII")).toEqual(7);
    });

    it('should return the number 12', () => {
        expect(executor.transformRomanToInteger("XII")).toEqual(12);
    });

    it('should return the number 58', () => {
        expect(executor.transformRomanToInteger("LVIII")).toEqual(58);
    });

    it('should return the number 1994', () => {
        expect(executor.transformRomanToInteger("MCMXCIV")).toEqual(1994);
    });

    it('should return the number 2024', () => {
        expect(executor.transformRomanToInteger("MMXXIV")).toEqual(2024);
    });

    it('should return the number 49', () => {
        expect(executor.transformRomanToInteger("XLIX")).toEqual(49);
    });
});
