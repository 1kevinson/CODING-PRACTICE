import { LeetCode } from '../../src/app.js';

let executor;

beforeEach(() => {
  executor = new LeetCode();
})

describe('Test cases', () => {
  test('test case 1', () => {
    expect(executor.singleNumber([2, 2, 1])).toBe(1);
  });

  test('test case 2', () => {
    expect(executor.singleNumber([4, 1, 2, 1, 2])).toBe(4);
  });

  test('test case 3', () => {
    expect(executor.singleNumber([1])).toBe(1);
  });
});

