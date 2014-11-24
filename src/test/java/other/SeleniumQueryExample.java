package other;

import static io.github.seleniumquery.SeleniumQuery.$;

public class SeleniumQueryExample {
    public static void main(String[] args) {
        // sets Firefox as the driver -- this is optional, if omitted, will default
        // to HtmlUnit or whatever you set at the, also optional, config files
        $.driver().useFirefox().withoutJavaScript();

        $.url("http://www.google.com/?hl=en");

        $(":text[name='q']").val("selenium"); // the keys are actually typed
        $(":button:contains('Google Search')").click();

        String resultsText = $("#resultStats").text();
        System.out.println(resultsText);

        // Besides the short syntax and the jQuery behavior you already know,
        // other very useful function in seleniumQuery is .waitUntil(),
        // handy for dealing with Ajax enabled pages:
        $(":input[name='q']").waitUntil().is(":enabled");
        // The line above waits for no time, as that input
        // is always enabled in google.com.

        $.quit(); // quits the currently used driver (firefox)
    }
}