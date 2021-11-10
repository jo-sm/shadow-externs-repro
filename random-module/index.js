function range(start, end) {
  const result = [];
  let curr = start;

  while (curr <= end) {
    result.push(curr++)
  }

  return result;
}

module.exports.coolFunction = function coolFunction(count) {
  return range(1, count).map(i => {
    return {
      someProperty: i
    }
  })
}
