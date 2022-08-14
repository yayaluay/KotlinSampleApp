# KotlinSampleApp

kotlinで作ったAndroidアプリケーション  
Github API ー「List users」（https://api.github.com/users ）にアクセスし、Githubユーザ一覧を取得してRecyclerViewで表示する  
リストをタップするとGithub API ー「Get a user」（https://api.github.com/users/USERNAME ）にアクセスし、タップしたユーザの詳細情報を取得して表示する  
利用したライブラリー  
 ・Volley：ネットワーク通信  
 ・Gson：jsonとデータオブジェクトを相互に変換する  
 ・Coil：画像を読み込む  
