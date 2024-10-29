package org.docksidestage.bizfw.basic.time;

import java.time.LocalDateTime;

/**
 * LocalDateTimeを取得するためのインターフェース <br>
 * テスト時は外部からLocalDateTimeを指定してそれを返すようにしたい、
 * それ以外は現在時刻を返すようにしたい場合に使える <br>
 * テスト時：TestTimeManager <br>
 * それ以外：CurrentTimeManger <br>
 * を使う
 * @author mayukorin
 */
public interface TimeManager {

    LocalDateTime getLocalDateTime();
}
