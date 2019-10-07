import React from 'react'

const TextField = ({type, name, onChange, onBlur, error, placeholder}) => (
    <div class ="textbox">
            <input
                type={type}
                name={name}
                placeholder={placeholder}
                onChange={onChange}
                onBlur={onBlur}
            />
            {error && <div style={{color: 'red'}}>{error}</div>}
    </div>
);

export default TextField;