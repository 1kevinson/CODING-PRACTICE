const { isEmpty } = require("lodash");

class Main {

  isValidParenthesis(pattern) {
    const stack = [];
    const parenthesis = pattern.split('');

    for (let character of parenthesis) {
      if (['(', '{', '['].includes(character)) {
        stack.push(character);
      } else {
        if (isEmpty(stack)) return false;

        const top = stack.pop();
        if ((character === ')' && top !== '(') ||
           (character === '}' && top !== '{') ||
           (character === ']' && top !== '[')) {
          return false;
        }
      }

    }

    return isEmpty(stack)
  }


}

module.exports = Main;