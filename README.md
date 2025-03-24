# PureValue

A comprehensive library of production–ready value objects for various domains including financial, commercial, shipping, postal, geo, internet, crypto, payment, and more.  
Each value object is **immutable**, **validated upon construction**, and follows best practices for **security**, **maintainability**, and **testability**.

---

## Features

### ✅ Domain-Specific Validation
Each value object implements strict validation logic including:
- Regex checks  
- Checksum algorithms  
- Range validations

### ✅ Immutable and Testable
Designed as `record` classes implementing the `PureValue<String>` interface, ensuring:
- Immutability  
- Predictable behavior  
- Easy testing  

### ✅ Comprehensive Coverage
Includes a wide range of identifiers:

- **Financial and Commercial**: `ISIN`, `EORI`, `LEI`, `NAICS`, `HS`, `SIC`, `CAGE`, `CFOP`, etc.  
- **Shipping & Logistics**: `ISO 6346 Container Code`, `Bill of Lading Number`  
- **Postal & Geographic**: Postal codes, `GeoCoordinates`, `TimeZone IDs`, `IBGE Code Brazil`  
- **Internet & Network**: `Email`, `UUID`, `Domain`, `URL`, `IPv4`, `IPv6`, `MAC Address`  
- **Crypto**: `Lightning Invoice`, `Bitcoin`, `Ethereum` addresses  
- **Payment**: Payment Transaction IDs, Receipt IDs  
- **And more…**

---

## Installation

Add the dependency to your **Maven** project:

```xml
<dependency>
  <groupId>io.github.arthurhoch</groupId>
  <artifactId>purevalue</artifactId>
  <version>1.0.1</version>
</dependency>
```

🔗 For more details, visit our [Sonatype OSS Index](https://oss.sonatype.org/).

---

## Usage

Each value object is available as a `record` with:

- Factory methods (`of`, `ofNullable`)  
- Validation methods (`isValid`)  
- Formatting (`format`)

### Example: ISIN

```java
import io.github.arthurhoch.purevalue.trade.isin.ISIN;

public class Example {
    public static void main(String[] args) {
        ISIN isin = ISIN.of("US0378331005");
        System.out.println("ISIN: " + isin.value());
        System.out.println("Formatted ISIN: " + isin.format());
    }
}
```

Other value objects follow the same pattern:

- ✅ `ISIN.of(...)` or `ISIN.ofNullable(...)`  
- ✅ `ISIN.isValid(...)`  
- ✅ `ISIN.format()`

---

## Contributing

Contributions, issues, and feature requests are **very welcome**!

- Found a bug? Open an [issue](https://github.com/arthurhoch/purevalue/issues) 🐛  
- Have an idea or improvement? Submit a **Pull Request**!  
- Need help or want to ask something? Feel free to reach out.

Let’s build a better, safer ecosystem of value objects together! 💪

---

## ☕ Support

If this project helps you, consider [buying me a coffee](https://buymeacoffee.com/arthurhoch) to support continued development and maintenance!

---

## License

Distributed under the **MIT License**.  
See [LICENSE](https://github.com/arthurhoch/purevalue/blob/main/LICENSE) for more information.

---

## Release

📦 Latest release: [Sonatype OSS Index - purevalue 1.0.1](https://oss.sonatype.org/service/local/lucene/search?a=purevalue&g=io.github.arthurhoch)

---

## 💬 Discussion

Use this section for internal notes, todos, or questions:

- [ ] Should we support more country-specific documents like CPF, CNPJ, CNH?
- [ ] Should we split modules into sub-artifacts like `purevalue-finance`, `purevalue-internet`, etc.?
- [ ] Add more examples in README?
- [ ] JavaDocs deployment?

---

✅ **Enjoy using PureValue** for robust and reliable domain-specific validations!
