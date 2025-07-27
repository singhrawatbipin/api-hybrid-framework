package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class CustomLogFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);

        if (response.statusCode() >= 400) {
            String log = "REQUEST: " + requestSpec.getURI() + "\n" +
                    "METHOD: " + requestSpec.getMethod() + "\n" +
                    "HEADERS: " + requestSpec.getHeaders() + "\n" +
                    "BODY: " + requestSpec.getBody() + "\n" +
                    "RESPONSE: " + response.asPrettyString();

            // ✅ Add log to console
            System.out.println(log);

            // ✅ Add log to Extent report
            TestListnerUtil.addLogToReport(log);
        }

        return response;
    }
}
