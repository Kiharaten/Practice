package local.hal.st31.android.favoriteshops80551;

/**
 * ST31 Androidサンプル12 メモ帳アプリ
 *
 * 店情報を格納するエンティティクラス。
 *
 * @author Shinzo SAITO
 */
public class Shop {
    /**
     * 主キーのID値。
     */
    private long _id;
    /**
     * 店名。
     */
    private String _name;
    /**
     * Tel。
     */
    private String _tel;
    /**
     * URL。
     */
    private String _url;
    /**
     * メモ。
     */
    private String _note;
    /**
     * 削除フラグ。
     */
    private String _flg;

    //以下アクセサメソッド。

    public long getId() { return _id; }
    public void setId(long id) { _id = id; }

    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    public String getTel() { return _tel; }
    public void setTel(String tel) { _tel = tel; }

    public String getUrl() { return _url; }
    public void setUrl(String url) { _url = url; }

    public String getNote() { return _note; }
    public void setNote(String note) { _note = note; }

    public String getFlg() { return _flg; }
    public void setFlg(String flg) { _flg = flg; }
}