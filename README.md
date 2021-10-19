Holidays with Java 8
========

Calculate the holidays of a country at runtime. No need to parse some special websites with these dates.


**Supported Counries**
 - Germany
 - Austria

**Sample**

This sample requests every german holiday, filters if it is a national one and sort it ascending.

```java
Country.GERMANY.getHolidays()
        .filter(holiday -> holiday.isNational())
        .map(holiday -> holiday.getDate(2021))
        .sorted()
        .forEach(System.out::println);
```

The Result:
<pre>
2021-01-01
2021-01-06
2021-04-04
2021-04-05
2021-04-06
2021-05-01
2021-05-13
2021-05-23
2021-05-24
2021-06-03
2021-08-15
2021-10-03
2021-10-31
2021-11-01
2021-11-17
2021-12-25
2021-12-26
2021-12-27
</pre>

License
=======
Copyright 2021 Markus Oswald

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
