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

DecimalFormat decimalFormat = new DecimalFormat("#,###.###")
String formattedResult = decimalFormat
        .format(COLUMNS.size() as BigDecimal)
        .replaceAll(" ", " ") // \u00a0 -> \u0020
        .replaceAll(",", ".")
OUT.append(formattedResult)