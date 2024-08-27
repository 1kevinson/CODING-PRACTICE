const Main = require("../../src/Main");

let executor;

beforeEach(() => {
    executor = new Main();
})

describe('Basic tests', () => {
    it('should return fl', () => {
        expect(executor.longestCommonPrefix(["flower", "flow", "flight"])).toEqual("fl");
    });

    it('should return empty', () => {
        expect(executor.longestCommonPrefix(["dog", "racecar", "car"])).toEqual("");
    });

    it('should return k', () => {
        expect(executor.longestCommonPrefix(["kog", "kacecar", "klar"])).toEqual("k");
    });
});
