#!/usr/bin/env python3
"""Rename EC rule keys to GCI and add @DeprecatedRuleKey for old EC keys."""
import os
import re

BASE = "android-plugin/src/main/java"

def process_file(filepath):
    with open(filepath, 'r') as f:
        content = f.read()

    # Find the EC key in @Rule annotation
    match = re.search(r'@Rule\(key\s*=\s*"EC(\d+)"', content)
    if not match:
        return

    num = match.group(1)
    old_key = f"EC{num}"
    new_key = f"GCI{num}"

    # Determine repository key based on path
    if "/xml/" in filepath:
        repo_key = "creedengo-android-xml"
    else:
        repo_key = "creedengo-android-java"

    # Replace @Rule key
    content = content.replace(f'@Rule(key = "{old_key}"', f'@Rule(key = "{new_key}"')

    # Check if DeprecatedRuleKey import exists
    if "org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey" not in content:
        # Add import
        content = content.replace(
            "import org.sonar.check.Rule;",
            "import org.sonar.check.Rule;\nimport org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;"
        )

    # Add new @DeprecatedRuleKey for old EC key before @Rule line
    new_deprecated = f'@DeprecatedRuleKey(repositoryKey = "{repo_key}", ruleKey = "{old_key}")'

    # Find the @Rule line and insert before it
    rule_pattern = f'@Rule(key = "{new_key}"'
    rule_idx = content.find(rule_pattern)
    if rule_idx == -1:
        return

    # Find the start of the line containing @Rule
    line_start = content.rfind('\n', 0, rule_idx) + 1
    indent = ''
    for ch in content[line_start:rule_idx]:
        if ch in ' \t':
            indent += ch
        else:
            break

    # Insert the new deprecated key annotation
    insert_text = f"{indent}{new_deprecated}\n"
    content = content[:line_start] + insert_text + content[line_start:]

    with open(filepath, 'w') as f:
        f.write(content)

    print(f"  {old_key} -> {new_key} in {os.path.basename(filepath)}")


def main():
    count = 0
    for root, dirs, files in os.walk(BASE):
        for fname in files:
            if fname.endswith('.java'):
                filepath = os.path.join(root, fname)
                with open(filepath, 'r') as f:
                    if '@Rule(key = "EC' in f.read():
                        process_file(filepath)
                        count += 1
    print(f"\nProcessed {count} files.")


if __name__ == "__main__":
    main()
