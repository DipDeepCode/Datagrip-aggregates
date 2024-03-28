import java.text.DecimalFormat

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

def RES = 0G
ROWS.each { row ->
  COLUMNS.each { column ->
    def value = row.value(column)
    if (value instanceof Number) {
      RES += 1
    }
    else if (value.toString().isBigDecimal()) {
      RES += 1
    }
  }
}
DecimalFormat decimalFormat = new DecimalFormat("#,###.###")
String formattedResult = decimalFormat
        .format(RES)
        .replaceAll(" ", " ")
        .replaceAll(",", ".")
OUT.append(formattedResult)