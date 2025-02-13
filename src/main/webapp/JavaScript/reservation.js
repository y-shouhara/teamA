/**
 * 
 */

// 7日分の要素をすべて取得
const links = document.getElementsByName("link");

// 各リンクにクリックイベントを追加
links.forEach(link => {
    link.addEventListener("click", function(event) {
        event.preventDefault(); // ＃のリンク動作を防ぐ
        // クリックされたリンクに対応するフォームを取得
		//nextElementSibling：次の兄弟要素を取得（thisは<a>）
        const form = this.nextElementSibling;
        form.submit();
    });
});