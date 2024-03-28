import java.text.DecimalFormat

import static java.math.MathContext.DECIMAL128

/*
 * Available context bindings:
 *   COLUMNS     List<DataColumn>
 *   ROWS        Iterable<DataRow>
 *   OUT         { append() }
 *   FORMATTER   { format(row, col); formatValue(Object, col); getTypeName(Object, col); isStringLiteral(Object, col); }
 *   TRANSPOSED  Boolean
 * plus ALL_COLUMNS, TABLE, DIALECT
 *
 * where:
 *   DataRow     { rowNumber(); first(); last(); data(): List<Object>; value(column): Object }
 *   DataColumn  { columnNumber(), name() }
 */

BigDecimal RES = 0
int i = 0
ROWS.each { row ->
  COLUMNS.each { column ->
    def value = row.value(column)
    if (value instanceof Number) {
      RES = RES.add(value as BigDecimal, DECIMAL128)
      i++
    }
    else if (value.toString().isBigDecimal()) {
      RES = RES.add(value.toString().toBigDecimal(), DECIMAL128)
      i++
    }
  }
}
if (i > 0) {
  RES = RES.divide(i as BigDecimal, DECIMAL128)
  DecimalFormat decimalFormat = new DecimalFormat("#,###.###")
  String formattedResult = decimalFormat
          .format(RES)
          .replaceAll(" ", " ") // \u00a0 -> \u0020
          .replaceAll(",", ".")
  OUT.append(formattedResult)
}
else {
  OUT.append("Not enough values")
}