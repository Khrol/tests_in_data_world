import importlib

from behave import given, when, then, use_step_matcher
import pandas as pd
from pandas.util.testing import assert_frame_equal

use_step_matcher('re')


def _str_to_class(full_path):
    parts = full_path.split('.')
    module_name = '.'.join(parts[:-1])
    class_name = parts[-1]
    module = importlib.import_module(module_name)
    return getattr(module, class_name)


def _table_to_dataframe(table):
    d = {}
    for row in table:
        for heading in row.headings:
            d[heading] = d.get(heading, []) + [row[heading]]
    return pd.DataFrame(data=d)


@given('input dataframe (?P<input_name>[-\w/]+)')
def step_impl(context, input_name):
    input = _table_to_dataframe(context.table)
    context.input = {**getattr(context, 'input', {}), **{input_name: input}}


@when('task is executed')
def step_impl(context):
    context.output = _str_to_class(context.feature.name)().calculate(**context.input)


@then('output equals to')
def step_impl(context):
    expected_result = _table_to_dataframe(context.table)
    expected_result['count'] = pd.to_numeric(expected_result['count'])
    assert_frame_equal(expected_result.sort_index(axis=1), context.output.sort_index(axis=1), check_names=True)
