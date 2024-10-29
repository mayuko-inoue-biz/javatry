package org.docksidestage.bizfw.basic.time;

import java.time.LocalDateTime;

// done mayukorin [いいね] javadoc素晴らしい！ by jflute (2024/10/29)
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

    // done mayukorin 一応呼び出し側は「現在日時」というのを意識して呼び出すので、名前にcurrentが入って欲しいですね by jflute (2024/10/29)
    // その上で、TestTimeManagerはcurrentの指し示す時刻を差し替えるということで。
    // 一方で、本物の現在日時を戻すCurrentTimeMangerは、RealなTimeManagerというニュアンスでしょうかね。
    LocalDateTime getCurrentDateTime();
}
